package yook.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import yook.common.map.CommandMap;

//사용자 요청이 컨트롤러에 도달하기 전에 그 요청의 파라미터들을 수정할 수 있게 해준다.
public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {
   @Override
   public boolean supportsParameter(MethodParameter parameter) {
      return CommandMap.class.isAssignableFrom(parameter.getParameterType());
   }
   
   @Override
   public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
      CommandMap commandMap = new CommandMap();
      
      HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
      Enumeration<?> enumeration = request.getParameterNames();
      
      String key = null;
      String[] values = null;
      while(enumeration.hasMoreElements()) {
         key = (String) enumeration.nextElement();
         values = request.getParameterValues(key);
         if(values!=null) {
            commandMap.put(key, (values.length>1)?values:values[0]);
         }
      }
      return commandMap;
   }
}