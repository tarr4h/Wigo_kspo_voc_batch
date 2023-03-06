package com.kspo.voc.batch.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName    UserBasVo
 * @author    김성태
 * @date    2023. 3. 6.
 * @Version    1.0
 * @description    사용자기본 Vo
 * @Company    Copyright ⓒ wigo.ai. All Right Reserved
 */
@Getter
@Setter
public class UserBasVo extends BaseVo {
    /**
    *
    */
private static final long serialVersionUID = 1L;
    /**
    * 사용자ID 
    */
    private String userId;
    /**
    * 로그인ID 
    */
    private String loginId;
    /**
    * 로그인비밀번호 
    */
    private String loginPwd;
    /**
    * 사용자명 
    */
    private String userNm;
    /**
    * 이메일주소 
    */
    private String emailAddr;
    /**
    * 이동전화번호 
    */
    private String mphonNo;
    /**
    * 사용자성별코드 
    */
    private String userGndrCd;
    /**
    * 사용자생년월일 
    */
    private String userBirthday;
    /**
    * 비밀번호수정일시 
    */
    private String pwdAmdDt;
    /**
    * 최종로그인일시 
    */
    private String lastLoginDt;
    /**
    * 비밀번호만료일시 
    */
    private String pwdExpDt;
    /**
    * 로그인실패수 
    */
    private Integer loginFailCnt;
    /**
    * 삭제여부 
    */
    private String delYn;
}
