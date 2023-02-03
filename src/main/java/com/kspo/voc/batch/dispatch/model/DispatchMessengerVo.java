package com.kspo.voc.batch.dispatch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispatchMessengerVo extends AbstractDispatchVo {
    private String code = "ECS";
    private String sendNM;
    private String sendID;
    private String recvID;
    private String notyTitle;
    private String notyContent;
    private String url;
    
}
