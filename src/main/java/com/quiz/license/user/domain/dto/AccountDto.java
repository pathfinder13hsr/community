package com.quiz.license.user.domain.dto;

import com.quiz.license.user.domain.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AccountDto {
    private Long seq;

    @NotBlank(message = "ID는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String userPw;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String userNickname;
    private String statusYn;
    private String adminYn;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;

    public AccountDto() {
    }

    @Builder(builderClassName = "byCreate", builderMethodName = "byCreate")
    public AccountDto(Long seq, String userId, String userPw, String userEmail, String userNickname, String statusYn, String adminYn, LocalDateTime createDatetime, LocalDateTime modifyDatetime) {
        this.seq = seq;
        this.userId = userId;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.statusYn = statusYn;
        this.adminYn = adminYn;
        this.createDatetime = createDatetime;
        this.modifyDatetime = modifyDatetime;
    }

    @Builder(builderClassName = "bySelect", builderMethodName = "bySelect")
    public AccountDto(AccountEntity accountEntity) {
        this.seq = accountEntity.getSeq();
        this.userId = accountEntity.getUserId();
        this.userPw = accountEntity.getUserPw();
        this.userEmail = accountEntity.getUserEmail();
        this.userNickname = accountEntity.getUserNickname();
        this.statusYn = accountEntity.getStatusYn();
        this.adminYn = accountEntity.getAdminYn();
        this.createDatetime = accountEntity.getCreateDatetime();
        this.modifyDatetime = accountEntity.getModifyDatetime();
    }

    @Builder(builderClassName = "bySelectMapper", builderMethodName = "bySelectMapper")
    public AccountDto(String id, String email, String userNickname, String statusYn) {
        this.userId = id;
        this.userEmail = email;
        this.userNickname = userNickname;
        this.statusYn = statusYn;
    }

    // Repository 를 사용해서 insert 를 하려면 Entity 로의 변환이 필요.
    // 등록일 컬럼 만들어서 등록일 LocalDatetime.now()로 넣어주세요.
    public AccountEntity byInsert() {
        return AccountEntity
                .builder()
                .userId(this.userId)
                .userPw(this.userPw)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname)
                .statusYn("Y")// -- N : 탈퇴유저, Y : 일반유저
                .adminYn("N") // -- N : 일반유저, Y : 관리자
                .createDatetime(LocalDateTime.now())
                .build();
    }

    // Repository 를 사용해서 update 를 하려면 Entity 로의 변환이 필요.
    // 수정일 컬럼 만들어서 수정일 LocalDatetime.now()로 넣어주세요. + 등록일은 그대로 DB에 있는 값 가져오기.
    public AccountEntity byUpdate(AccountEntity selectData) {
        return AccountEntity
                .builder()
                // DB에서 시퀀스를 PK로 걸어두고 entity에 Id 라고 지정해줬을때, seq 같은 값이 있으면 해당 row 를 업데이트 한다
                .seq(selectData.getSeq())
                .userId(selectData.getUserId())
                .userPw(this.userPw)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname)
                .statusYn(selectData.getStatusYn())
                .adminYn(selectData.getAdminYn())
                .createDatetime(selectData.getCreateDatetime())
                .modifyDatetime(LocalDateTime.now())
                .build();
    }

    public AccountEntity byUpdateByAdmin(AccountEntity selectData) {
        return AccountEntity
                .builder()
                .seq(this.seq)
                .userId(selectData.getUserId())
                .userPw(selectData.getUserPw())
                .userEmail(selectData.getUserEmail())
                .userNickname(selectData.getUserNickname())
                .statusYn(this.statusYn)
                .adminYn(this.adminYn)
                .createDatetime(selectData.getCreateDatetime())
                .modifyDatetime(LocalDateTime.now())
                .build();
    }

}
