package com.example.demo.security;

public class TenantContext {
    //ThreadLocal giup moi request HTTP co mot khong gian nho doc loc, ko bi lan lon giua nhieu user
    private static final ThreadLocal<Long> CURRENT_TENANT= new ThreadLocal<>();
    public static void setCurrentTenant(Long tenantId) {
        CURRENT_TENANT.set(tenantId);
    }
    public static Long getCurrentTenant() {
        return CURRENT_TENANT.get();
    }
    public static void clear() {
        CURRENT_TENANT.remove();
    }
}


// Khi bảo vệ đọc được thẻ và biết User này thuộc organizationId = 1, chúng ta không thể cứ truyền số 1 này rườm rà qua từng hàm Controller xuống Service được.
// Bạn hãy tạo file TenantContext.java trong package security để lưu ID công ty dùng chung cho toàn bộ một chu kỳ request: