package com.aplusplus.HotelBooking.service.interf;

import com.aplusplus.HotelBooking.dto.PromotionDTO;
import com.aplusplus.HotelBooking.dto.Response;
import org.springframework.data.domain.Pageable;

public interface IPromotionService {
    Response createPromotionForRoomType(PromotionDTO promotion, String[] listRoomType); // thêm list các roomId
    Response getPromotionById(String promotionId); // Admin xem chi tiết promotion
    Response getAllPromotion(Pageable pageable); // Admin
    Response updatePromotion(PromotionDTO promotion, String promotionId); // Admin
    Response deletePromotion(String promotionId);
    Response applyPromotionToRoom(String promotionId, String roomId); // Liên quan Booking
    Response getPromotionByRoomId(String roomId); // List các promotion áp dụng cho 1 room
    Response getLatestPromotion(Pageable pageable); // User xem promotion mới nhất
}
