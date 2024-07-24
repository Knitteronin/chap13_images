package com.javalab.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * @ControllerAdvice : 전역적으로 컨트롤러 예외를 처리하는 클래스
 * @ExceptionHandler : 예외 처리를 위한 메소드를 정의하고, 
 * @ExceptionHandler 어노테이션을 사용하여 예외 처리
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

	/**
	 * Exception 예외 처리 전용 클래스
	 * - 컨트롤러 차원의 예외를 전담해서 처리해준다.
	 * - 전역적으로 컨트롤러의 이외의 예외 처리할 수 있다. 예를들면 데이터바이딩.
	 * - 서비스단, 리파지토리단에서 예외를 던지면 컨트롤러로 예외 전파되고 그 예외를
	 *   예외 전용 클래스가 잡아준다.(서비스, 리포지토리는 예외 던져줘야 한다.)
	 * - 예외 처리를 위한 메소드를 정의하고, @ExceptionHandler 어노테이션을 사용하여 예외 처리
	 * - Exception.class : 모든 예외의 최상위 클래스, 즉 모든 예외를 처리할 수 있음
	 */
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {

		log.error("Exception ......." + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model.toString());
		return "error_page";
	}

	/**
	 * 404 에러 처리
	 * - 해당 경로에 매핑되는 컨트롤러가 없을 때 발생하는 예외
	 * - 예외 처리를 위한 메소드를 정의하고, @ExceptionHandler 어노테이션을 사용하여 예외 처리
	 * - NoHandlerFoundException : 요청에 대한 핸들러를 찾을 수 없는 경우 발생하는 예외
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex, Model model) {
		model.addAttribute("exception", ex);
		return "custom404";
	}

}
