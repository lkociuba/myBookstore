
package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.BookService;
import com.example.myBookstore.web.dto.BookAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/bookAdd")
public class BookAddController {

    @Autowired
    private BookService bookService;

    @ModelAttribute("book")
    public BookAddDto bookAddDto(){
        return new BookAddDto();
    }

    @GetMapping
    public String showBookAddSide(){
        return "bookAdd";
    }

    @PostMapping
    public String addNewBook(@Valid @ModelAttribute("book")BookAddDto bookAddDto,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "bookAdd";
        }

        bookService.saveBook(bookAddDto);
        return "redirect:/bookAdd?success";
    }
}

