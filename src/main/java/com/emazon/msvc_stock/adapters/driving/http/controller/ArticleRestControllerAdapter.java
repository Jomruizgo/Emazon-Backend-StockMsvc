package com.emazon.msvc_stock.adapters.driving.http.controller;

import com.emazon.msvc_stock.adapters.driving.http.dto.request.AddArticleRequestDto;
import com.emazon.msvc_stock.adapters.driving.http.dto.request.AddStockRequestDto;
import com.emazon.msvc_stock.adapters.driving.http.dto.response.ArticleResponseDto;
import com.emazon.msvc_stock.adapters.driving.http.mapper.request.IArticleRequestMapper;
import com.emazon.msvc_stock.adapters.driving.http.mapper.response.IArticleResponseMapper;
import com.emazon.msvc_stock.domain.api.IArticleServicePort;
import com.emazon.msvc_stock.domain.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_ARTICLE_PATH)
public class ArticleRestControllerAdapter {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;

    public ArticleRestControllerAdapter(IArticleServicePort articleServicePort, IArticleRequestMapper articleRequestMapper, IArticleResponseMapper articleResponseMapper) {
        this.articleServicePort = articleServicePort;
        this.articleRequestMapper = articleRequestMapper;
        this.articleResponseMapper = articleResponseMapper;
    }
    @PostMapping("")
    public ResponseEntity<Void> addArticle(@Valid @RequestBody AddArticleRequestDto request){
        articleServicePort.saveArticle(articleRequestMapper.addRecuestToArticle(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles(@RequestParam Integer page,
                                                                   @RequestParam Integer size,
                                                                   @RequestParam(required = false) String sortBy ,
                                                                   @RequestParam(required = false) String order){
        return ResponseEntity.ok(articleResponseMapper.toArticleResponseList(articleServicePort.listArticles(sortBy, order, page, size)));
    }


    @PostMapping("/supply")
    public ResponseEntity<Boolean> increaseArticleStock(@RequestBody AddStockRequestDto stockRequest) {
        try {
            boolean success = articleServicePort.increaseArticleStocks(stockRequest.getArticleId(), stockRequest.getQuantity());
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
