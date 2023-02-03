package com.kspo.voc.batch.dispatch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispatchEmailVo extends AbstractDispatchVo  {
    private String sysGubun = "ECS";
    private String sendSubject;
    private String sendPhoneNumber;
    private String sendUserId;
    private String sendUserName;
    private String receivePhoneNumber;
    private String receiveName;

}
