package vip.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vip.atguigu.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

}
