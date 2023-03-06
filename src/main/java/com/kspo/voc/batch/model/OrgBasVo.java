package com.kspo.voc.batch.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName    OrgBasVo
 * @author    김성태
 * @date    2023. 3. 6.
 * @Version    1.0
 * @description    조직기본 Vo
 * @Company    Copyright ⓒ wigo.ai. All Right Reserved
 */
@Getter
@Setter
public class OrgBasVo extends BaseVo {
    /**
    *
    */
private static final long serialVersionUID = 1L;
    /**
    * 조직ID 
    */
    private String orgId;
    /**
    * 조직명 
    */
    private String orgNm;
    /**
    * 조직분류ID 
    */
    private String orgClassId;
    /**
    * 조직분류명 
    */
    private String orgClassNm;
    /**
    * 레벨번호 
    */
    private Integer lvlNo;
    /**
    * 조직순번 
    */
    private Integer orgOdrg;
    /**
    * 상위조직ID 
    */
    private String upOrgId;
    /**
    * 상위조직명 
    */
    private String upOrgNm;
    /**
    * 조직사원ID 
    */
    private String orgEmpId;
    /**
    * 조직사원명 
    */
    private String orgEmpNm;
    /**
    * 시작년월일 
    */
    private String staYmd;
    /**
    * 종료년월일 
    */
    private String endYmd;
    
}
