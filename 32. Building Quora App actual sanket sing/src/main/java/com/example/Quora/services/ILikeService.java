package com.example.Quora.services;

import com.example.Quora.dto.LikeRequestDTO;
import com.example.Quora.dto.LikeResponseDTO;
import com.example.Quora.models.Like;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {
    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Mono<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, String targetType);
    Mono<LikeResponseDTO> countDislikesByTargetIdAndTargetType(String targetId, String targetType);
    Mono<LikeResponseDTO> toggleLike(String targetId, String targetType, Boolean isLike);
}
