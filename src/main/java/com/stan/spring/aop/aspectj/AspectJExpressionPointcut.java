package com.stan.spring.aop.aspectj;

import com.stan.spring.aop.ClassFilter;
import com.stan.spring.aop.MethodMatcher;
import com.stan.spring.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: AspectJExpressionPointcut
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES  = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(SUPPORTED_PRIMITIVES);
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean match(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean match(Method method, Class<?> clazz) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

}
