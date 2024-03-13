package com.example.blogjpa.controller;

import com.example.blogjpa.Service.BlogService;
import com.example.blogjpa.domain.Article;
import com.example.blogjpa.dto.AddArticleRequest;
import com.example.blogjpa.dto.ArticleResponse;
import com.example.blogjpa.dto.ArticleViewResponse;
import com.example.blogjpa.dto.UpdateArticleRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController		// HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogController {
    private BlogService blogService;
    private Logger log;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // HTTP요청이 'POST /api/articles' 경로일 때 해당 메소드로 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) { // RequestBody로 요청 본문 값 매핑
        Article article = blogService.save(request);
        ArticleResponse savedResponse = article.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedResponse);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> showOneArticle(@PathVariable Long id){
        Article article = blogService.findById(id);
        ArticleResponse response = article.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping ("/api/articles/{id}")
    public ResponseEntity<Void> deleteOneArticle(@PathVariable Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedArticle);
    }


}