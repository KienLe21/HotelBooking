package com.aplusplus.HotelBooking.controller;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;
import com.aplusplus.HotelBooking.service.interf.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final IPromotionService promotionService;

    // user service createPromotion
    @PostMapping("/create-promotion")
    public ResponseEntity<Response> createPromotion(
            @RequestPart(value = "promotion") PromotionDTO promotion,
            @RequestPart(value = "imageFile") MultipartFile imageFile) {
        Response response = promotionService.createPromotionForRoomType(promotion, promotion.getListRoomTypes(), imageFile);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // use service getAllPromotion
    @GetMapping("/get-all-promotions")
    public ResponseEntity<Response> getAllPromotion(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        Response response = promotionService.getAllPromotion(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service getPromotionById
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Response> getPromotion(@PathVariable("id") String id){
        Response response = promotionService.getPromotionById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service updatePromotion
    // still has bugs, when removing room type from promotion list room type, it still remains in the list
    @PostMapping("/update-promotion/{id}")
    public ResponseEntity<Response> updatePromotion(
            @RequestPart(value = "promotion") PromotionDTO promotion,
            @RequestPart(value = "imageFile") MultipartFile imageFile,
            @PathVariable("id") String id) {
        Response response = promotionService.updatePromotion(promotion, id, imageFile);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service deletePromotion
    @PostMapping("/delete-promotion/{id}")
    public ResponseEntity<Response> deletePromotion(@PathVariable("id") String id){
        Response response = promotionService.deletePromotion(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // user service applyPromotionToRoom
    @GetMapping("/get-promotion-by-room/{room_id}")
    public ResponseEntity<Response> getPromotionByRoom(@PathVariable("room_id") String room_id) {
        Response response = promotionService.getPromotionByRoomId(room_id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-latest-promotion")
    public ResponseEntity<Response> getLatestPromotion(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        Response response = promotionService.getLatestPromotion(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
