package com.yueshen.testprocessors;

import com.google.auto.service.AutoService;
import com.yueshen.testannotation.FindId;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 在Java 7中，你也可以使用注解来代替getSupportedAnnotationTypes()和getSupportedSourceVersion()
 *
 * @SupportedSourceVersion(SourceVersion.latestSupported())
 * @SupportedAnnotationTypes({ // 合法注解全名的集合   })
 * 因为兼容的原因，特别是针对Android平台，我建议使用重载方法
 */
@AutoService(Process.class)
public class XAnnotationProcessor extends AbstractProcessor {

    private Filer filer;//跟文件相关的辅助类，生成JavaSourceCode.
    private Messager messager;//跟日志相关的辅助类。
    private Elements elements;//跟元素相关的辅助类，帮助我们去获取一些元素相关的信息。

    /*
    Element
        - VariableElement   //一般代表成员变量
        - ExecutableElement //一般代表类中的方法
        - TypeElement       //一般代表代表类
        - PackageElement    //一般代表Package
    */

    /**
     * 每一个注解处理器类都必须有一个空的构造函数。然而，这里有一个特殊的init()方法，它会被注解处理工具调用，并输入ProcessingEnviroment参数
     *
     * @param processingEnvironment 提供很多有用的工具类Elements, Types和Filer
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elements = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
    }

    /**
     * 这里你必须指定，这个注解处理器是注册给哪个注解的。
     * 注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称。
     * 换句话说，你在这里定义你的注解处理器注册到哪些注解上。
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotation = new LinkedHashSet<>();
        annotation.add(FindId.class.getCanonicalName());
        return annotation;
    }

    /**
     * 用来指定你使用的Java版本。通常这里返回SourceVersion.latestSupported()。
     * 然而，如果你有足够的理由只支持Java 6的话，你也可以返回SourceVersion.RELEASE_6。我推荐你使用前者。
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Map<String, XElementInfo> elementInfoMap;

    /**
     * 这相当于每个处理器的主函数main()。你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件
     *
     * @param set
     * @param roundEnvironment 可以让你查询出包含特定注解的被注解元素
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (elementInfoMap == null)
            elementInfoMap = new HashMap<>();
        elementInfoMap.clear();

        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(FindId.class);
        for (Element e : elementSet) {
            if (!checkAnnotationUseValid(e))
                return false;
        }

        return false;
    }

    ////检查element类型
    private boolean checkAnnotationUseValid(Element element) {
        // 检查被注解为@FindId的元素是否是一个变量
        if (element.getKind() != ElementKind.FIELD)
            return false;

        return true;
    }

    //错误信息输出
    private void error(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }
}
