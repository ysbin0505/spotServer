package com.example.spotserver.service;

import com.example.spotserver.domain.Inquiry;
import com.example.spotserver.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryService {

    private InquiryRepository inquiryRepository;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public Long addInquiry(Inquiry inquiry) {
        Inquiry saveInquiry = inquiryRepository.save(inquiry);
        return saveInquiry.getId();
    }

    public List<Inquiry> getInquirys() {
        List<Inquiry> inquiries = inquiryRepository.findAll();
        return inquiries;
    }

    public Inquiry getInquiry(Long id) {
        Optional<Inquiry> inquiry = inquiryRepository.findById(id);
        return inquiry.get();
    }
}
