package org.container.platform.common.api.common;

import lombok.Data;

/**
 * ResultStatus Model 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2022.06.09
 **/
@Data
public class ResultStatus {
    private String resultCode;
    private String resultMessage;
    private int httpStatusCode;
    private String detailMessage;
    private String nextActionUrl;

    public ResultStatus() {
    }

    /**
     * Instantiates a new ResultStatus
     *
     * @param resultCode the result code
     * @param resultMessage the result message
     * @param httpStatusCode the http status code
     * @param detailMessage the detail message
     */
    public ResultStatus(String resultCode, String resultMessage, int httpStatusCode, String detailMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatusCode = httpStatusCode;
        this.detailMessage = detailMessage;
    }

    /**
     * Instantiates a new ResultStatus
     *
     * @param resultCode the result code
     * @param resultMessage the result message
     * @param httpStatusCode the http status code
     * @param detailMessage the detail message
     * @param nextActionUrl the next action url
     */
    public ResultStatus(String resultCode, String resultMessage, int httpStatusCode, String detailMessage, String nextActionUrl) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatusCode = httpStatusCode;
        this.detailMessage = detailMessage;
        this.nextActionUrl = nextActionUrl;
    }

    public ResultStatus(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.detailMessage = resultMessage;
        this.nextActionUrl = Constants.NULL_REPLACE_TEXT;
    }


}