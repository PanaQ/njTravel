package travel.nanjing.com.travel.api.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/*
 * 此注解表示下面的接口需要使用HTTPS访问
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface HTTPS {
}
