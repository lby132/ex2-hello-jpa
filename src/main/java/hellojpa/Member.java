package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Member {

    //== 기본키 매핑 ==
    //@Id만 쓰면 직접할당
    //@GeneratedValue 자동생성 AUTO면 DB방언에 맞춰서 자동생성. IDENTITY는 기본키 생성을 데이터베이스에 위임. ex)MySQL의 Auto_increment
    //IDENTITY전략은 id값이 null일때 아이디값을 생성하는데 영속성 컨텍스트에 들어가려면 아이디 값이 있어야한다.
    //insert쿼리를 날려봐야 id값을 알수있기때문에 이 경우에는(IDENTITY전략일 경우) em.persist(member)를 하면 insert쿼리가 날라나게되고,
    //아이디값을 가져와서 영속성컨텍스트에서 pk값으로 쓰게 된다.
    //한트랜잭션 안에서 버퍼링해서 write하는게 그렇게 성능에 이점이 있지 않아서 괜찮다고 함.
    //다른 전략은 커밋하는 시점에 insert쿼리가 날라감.
    @Id @GeneratedValue
    private Long id;

    //== Column옵션 ==
    //updatable = false Jpa를 사용할때 변경안되게 막음
    //nullable = false not null 제약조건이 걸림
    //unique = true 유니크 제약조건을 걸때 사용. 하지만 복잡한 이름으로 나와서 사용안함.
    //클래스 레벨에 @Table(uniqueConstraints = ) 테이블에서 유니크 제약조건을 걸면 이름 설정가능.
    //데이터베이스 컬럼정보를 직접 줄 수 있음. ex) columnDefinition = "varchar(100) default 'EMPTY'"
    //length도 설정 가능
    //소수점이나 엄청큰 숫자를 사용할때 타입으로 BigDecimal을 줄 수 있음.
    //DB에는 enum타입이 없지만 @Enumerated으로 enum을 사용할 수 있음.
    //그런데 @Enumerated(EnumType.STRING)으로 사용해서 이름을 DB에 저장해야함 디폴트가 ORDINAL인데 이건 순서를 DB에 저장하기 때문에
    //만약 순서가 바뀐다면 원하는 데이터를 보지 못함.
    //@Temporal 대신 최신 하이버네이트를 쓰면 LocalDate(년/월) 타입을 쓰면 된다. = DB에 date타입으로 저장됨
    //@Temporal 대신 최신 하이버네이트를 쓰면 LocalDateDateTime(년/월/일) 타입을 쓰면 된다. = DB에 timestamp로 저장
    //@Lob는 데이터베이스의 BLOB와 CLOB와 매핑된다.
    //만약 매핑하는 필드 타입이 문자면 CLOB로 매핑되고 ex) String, char[] 나머지는 BLOB로 매핑된다. ex) byte[]
    //@Transient  //DB와 상관없이 메모리에서만 사용하고 싶을때 임시로 어떤 값을 보관하고 싶을때 사용. (DB저장 X, 조회 X, 필드매핑 X)
    @Column(name = "name", updatable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member() {
    }
}
