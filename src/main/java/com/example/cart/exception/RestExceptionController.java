package com.example.cart.exception;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionController {

	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<List<ExceptionBody>> handleInternalServerException(InternalServerException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("Please try again later");

		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
		exceptionBodies.add(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 500,
				"HTTP: Internal server error", exception.getMsg()));

		return new ResponseEntity<>(exceptionBodies, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<List<ExceptionBody>> handleAlreadyExistsException(AlreadyExistsException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("Request is already fullfiled");
		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
		exceptionBodies.add(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 208, "HTTP: Already Reported",
				exception.getMsg()));

		return new ResponseEntity<>(exceptionBodies, HttpStatus.ALREADY_REPORTED);
	}

	@ExceptionHandler(PayloadException.class)
	public ResponseEntity<List<ExceptionBody>> handlePayloadException(PayloadException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("In appropriate payload!");
		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
		exceptionBodies.add(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 400, "HTTP: Bad Request",
				exception.getMsg()));

		return new ResponseEntity<>(exceptionBodies, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<List<ExceptionBody>> handleNotFoundException(NotFoundException exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("Not Found Exception");

		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
		exceptionBodies.add(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 404, "HTTP: Page Not Found",
				exception.getMsg()));

		return new ResponseEntity<>(exceptionBodies, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoContentFound.class)
	public ResponseEntity<List<ExceptionBody>> handleNoContentFound(NoContentFound exception) {
		if (exception.getMsg().isEmpty())
			exception.setMsg("No Content Found Exception");

		final List<ExceptionBody> exceptionBodies = new ArrayList<ExceptionBody>();
		exceptionBodies.add(new ExceptionBody(new Timestamp(System.currentTimeMillis()), 204, "HTTP: No Content Found!",
				exception.getMsg()));

		return new ResponseEntity<>(exceptionBodies, HttpStatus.NO_CONTENT);
	}

}