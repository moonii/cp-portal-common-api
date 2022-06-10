package org.paasta.container.platform.common.api.exception;

/**
 * Container Platform Exception Model 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2022.06.02
 **/
public class ResultStatusException extends BaseBizException {
	private static final long serialVersionUID = -1288712633779609678L;

	private String resultMessage;

	public ResultStatusException(String resultMessage) {
		super(resultMessage);
		this.resultMessage =resultMessage; }

}
