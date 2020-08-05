package com.ayshiktest.util;

import com.ayshiktest.exception.CustomGeneralException;
import com.ayshiktest.model.PhoneNumberModel;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AyshikUtil {

    @Autowired
    DozerBeanMapper mapper;

    public <FROM, TO> List<TO> mapList(List<FROM> fromList, final Class<TO> toClass) {
        return fromList
                .stream()
                .map(from -> mapper.map(from, toClass))
                .collect(Collectors.toList());
    }

    public void validateIncomingPhoneNumberList(List<PhoneNumberModel> phones) throws CustomGeneralException {

        if(phones.size() > 4) throw new CustomGeneralException("We support only 4 numbers per contact!!");
        int mobileCount = 0;
        int workCount = 0;
        for (PhoneNumberModel phoneNumberModel : phones) {
            if (phoneNumberModel.getPhoneNumberType().equalsIgnoreCase("Mobile")) mobileCount++;
            if (phoneNumberModel.getPhoneNumberType().equalsIgnoreCase("Work")) workCount++;
        }
        if (mobileCount > 2)  throw new CustomGeneralException("We support only 2 mobile numbers per contact!!");
        if (workCount > 2)  throw new CustomGeneralException("We support only 2 work numbers per contact!!");
    }
}
