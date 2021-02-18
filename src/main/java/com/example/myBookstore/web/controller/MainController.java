package com.example.myBookstore.web.controller;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/bookList")
    public String showProductList(ModelMap model) {
        model.put("books", bookService.getAllBooks());
        return "bookList";
    }

    @GetMapping("/")
    public String showWelcomePage(ModelMap model) {
        model.put("userName", getLoggedinUserNamer());
        return "index";
    }

    private String getLoggedinUserNamer() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @GetMapping("/paginatedBookList")
    public String findPaginatedBooks(ModelMap model) {
        return showPaginatedBooks(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNumber}")
    public String showPaginatedBooks(@PathVariable(value = "pageNumber") int pageNumber,
                                     @RequestParam("sortField") String sortField,
                                     @RequestParam("sortDirection") String sortDirection,
                                     ModelMap model) {
        int pageSize = 5;

        Page<Book> page = bookService.findAllBooksPaginategAndSorted(pageNumber, pageSize, sortField, sortDirection);
        List<Book> bookList = page.getContent();


        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("bookList", bookList);

        return "paginatedBookList";
    }
}
