//package cp.demo.service;
//
//import cp.demo.domain.entity.MenuEntity;
//import cp.demo.domain.entity.MyMealEntity;
//import cp.demo.domain.repository.MenuRepository;
//import cp.demo.domain.repository.MyMealRepository;
//import cp.exception.RecommendServiceErrorResult;
//import cp.exception.RecommendServiceException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.awt.*;
//
//@RequiredArgsConstructor
//@Service
//@Transactional(readOnly = true)
//public class RecommendService {
//
//    private final MenuRepository menuRepository;
//
//
//    public MyMealEntity defaultRecommend(){
//
//        if(menuRepository.defaultRecommend() == null){
//            throw new RecommendServiceException(RecommendServiceErrorResult.NO_DATA);
//        }
//
//        return null;
//    }
//
//}
//
