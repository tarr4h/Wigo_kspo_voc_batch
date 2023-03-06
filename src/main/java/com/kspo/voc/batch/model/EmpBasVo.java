package com.kspo.voc.batch.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName EmpBasVo
 * @author 김성태
 * @date 2023. 3. 6.
 * @Version 1.0
 * @description 사원기본 Vo
 * @Company Copyright ⓒ wigo.ai. All Right Reserved
 */
@Getter
@Setter
public class EmpBasVo extends BaseVo {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     * 직원부서ID
     */
    private String empOrgId;
    /**
     * 사원ID
     */
    private String empId;
    /**
     * 로그인ID
     */
    private String loginId;
    /**
     * 로그인비밀번호 
     */
     private String loginPwd;
    /**
     * 조직ID
     */
    private String orgId;
    /**
     * 겸직부서
     */
    private String orgCncrt;
    /**
     * 결직여부
     */
    private String cncrtYn;
    /**
     * 사원명
     */
    private String empNm;
    /**
     * 회사코드
     */
    private String cmpCd;
    /**
     * 직업ID
     */
    private String jobId;
    /**
     * 직무코드
     */
    private String dutyCd;
    /**
     * 전화번호
     */
    private String telNo;
    /**
     * 사원등급코드
     */
    private String empGradeCd;
    /**
     * 생년월일
     */
    private String birthday;
    /**
     * 이메일주소
     */
    private String emailAddr;
    /**
     * 상태코드
     */
    private String statusCd;
    /**
     * 입사년월일
     */
    private String encoYmd;
    /**
     * 퇴사년월일
     */
    private String retireYmd;
    /**
     * 사원유형코드
     */
    private String empTypeCd;
    /**
     * 이동전화번호
     */
    private String mphonNo;
}
