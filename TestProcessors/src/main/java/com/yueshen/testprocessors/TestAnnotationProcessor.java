package com.yueshen.testprocessors;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * 在Java 7中，你也可以使用注解来代替getSupportedAnnotationTypes()和getSupportedSourceVersion()
 *  @SupportedSourceVersion(SourceVersion.latestSupported())
    @SupportedAnnotationTypes({   // 合法注解全名的集合   })
    因为兼容的原因，特别是针对Android平台，我建议使用重载方法
 */
public class TestAnnotationProcessor extends AbstractProcessor {

    /**
     * 这里你必须指定，这个注解处理器是注册给哪个注解的。
     * 注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称。
     * 换句话说，你在这里定义你的注解处理器注册到哪些注解上。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    /**
     * 用来指定你使用的Java版本。通常这里返回SourceVersion.latestSupported()。
     * 然而，如果你有足够的理由只支持Java 6的话，你也可以返回SourceVersion.RELEASE_6。我推荐你使用前者。
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 每一个注解处理器类都必须有一个空的构造函数。然而，这里有一个特殊的init()方法，它会被注解处理工具调用，并输入ProcessingEnviroment参数
     *
     * @param processingEnvironment 提供很多有用的工具类Elements, Types和Filer
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    /**
     * 这相当于每个处理器的主函数main()。你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件
     * @param set
     * @param roundEnvironment 可以让你查询出包含特定注解的被注解元素
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
