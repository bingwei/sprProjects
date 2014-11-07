package com.smart.concept;

import com.smart.domain.User;

public interface SecurityService {
    boolean checkAccess(User user, String service);
}
