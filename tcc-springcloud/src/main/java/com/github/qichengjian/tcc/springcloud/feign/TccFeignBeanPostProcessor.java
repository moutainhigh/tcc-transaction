package com.github.qichengjian.tcc.springcloud.feign;

import com.github.qichengjian.tcc.common.annotation.Tcc;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * tcc feign bean post processor
 */
public class TccFeignBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 不是代理对象则直接返回
        if (!Proxy.isProxyClass(bean.getClass())) {
            return bean;
        }
        InvocationHandler handler = Proxy.getInvocationHandler(bean);

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());

        for (Method method : methods) {
            Tcc tcc = AnnotationUtils.findAnnotation(method, Tcc.class);
            //处理带有tcc注解的方法
            if (Objects.nonNull(tcc)) {
                TccFeignHandler tccFeignHandler = new TccFeignHandler();
                tccFeignHandler.setDelegate(handler);
                Class<?> clazz = bean.getClass();
                Class<?>[] interfaces = clazz.getInterfaces();
                ClassLoader classLoader = clazz.getClassLoader();
                return Proxy.newProxyInstance(classLoader,interfaces,tccFeignHandler);
            }
        }
        return bean;
    }
}
