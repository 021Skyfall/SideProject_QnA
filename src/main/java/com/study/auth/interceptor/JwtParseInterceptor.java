package com.study.auth.interceptor;

import com.study.auth.utils.ErrorResponder;
import com.study.auth.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.util.Map;

// TODO: 2023-04-02 이 클래스는 뭐임

/**
 * Controller에서 인증된 member의 memberId를 이용할 수 있게 해주는 Interceptor
 * - HTTP Request에 포함된 JWT를 parsing해서 memberId를 얻는다.
 * - memberId를 ThreadLocal에 저장한다.
 *      - 저장된 ThreadLocal은 Controller에서 사용할 수 있다.
 *      - ThreadLocal은 멀티 쓰레딩 환경에서 해당 Thread만 사용할 수 있는 지역변수이다.
 *      - Spring MVC의 경우 HTTP request당 하나의 Thread가 매핑되므로 JWT의 claims를 담기 좋은 포인트임.
 *      - HTTP Request에서 JWT를 얻은 후에 Controller에서 사용할 수는 있지만 이 경우 다음과 같은 불편함이 발생한다.
 *          - 코드가 지저분해진다.
 *              - Controller 핸들러 메서드의 파라미터로 매번 HttpServletRequest를 지정해줘야 한다.
 *              - HttpServletRequest의 header에서 JWT를 얻는 로직이 매번 추가된다.
 *              - JWT를 파싱해서 memberId를 얻는 로직이 매번 추가된다.
 */

@Slf4j
@Component
@AllArgsConstructor
public class JwtParseInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;
    private static final ThreadLocal<Long> authenticatedMemberId = new ThreadLocal<>();

    public static long getAuthenticatedMemberId() {
        return authenticatedMemberId.get();
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
       try {
           Map<String, Object> claims = jwtUtils.getJwsClaimsFromRequest(request);
           authenticatedMemberId.set(Long.valueOf(claims.get("memberId").toString()));
           return true;
       } catch (Exception e) {
           /**
            * JWT 검증 및 Expiration 시, Error Response 전송.
            * - GlobalExceptionAdvice에서 처리할 수 있으나 보안과 관련된 에러는 이미 만들어 둔 ErrorResponder룰 사용해서 중복 로직을 최소화 한다.
            */

           ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
           return false;
       }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        this.authenticatedMemberId.remove();
    }
}
