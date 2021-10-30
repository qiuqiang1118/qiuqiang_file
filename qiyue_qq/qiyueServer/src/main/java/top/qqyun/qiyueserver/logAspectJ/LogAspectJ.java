package top.qqyun.qiyueserver.logAspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 邱强
 * @time 2021/10/29
 */
public class LogAspectJ {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* top.qqyun.qiyueserver.controller.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
//        logger.info("---------before---------");
        RequestLog requestLog = new RequestLog(
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                classMethod,
                joinPoint.getArgs()
        );
        logger.info("-------------"+requestLog+"------------");
    }

    @After("log()")
    public void After(){
        logger.info("---------After---------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void AfterReturn(Object result){
        logger.info("Result--->{}",result);
    }


    private class RequestLog{

        private String url;
        private String ip;
        private String classMethod;
        private Object args[];
        public RequestLog() {
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}