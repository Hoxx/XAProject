package com.yueshen.xprocessor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.yueshen.xannotation.FindId;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

/*对应的是一个类，如果在一个类中多个地方用到了这个注解，那么会把左右的注解信息，加入到这个annotationMapInfo里面*/
public class XAnnotationClassItem {

    private PackageElement packageElement;
    private TypeElement typeElement;
    private Messager messager;

    private Map<Integer, VariableElement> annotationMapInfo = new HashMap<>();

    public XAnnotationClassItem(PackageElement packageElement, TypeElement typeElement) {
        this.packageElement = packageElement;
        this.typeElement = typeElement;
    }

    public XAnnotationClassItem(PackageElement packageElement, TypeElement typeElement, Messager messager) {
        this.packageElement = packageElement;
        this.typeElement = typeElement;
        this.messager = messager;
    }

    //收集一个类中的所有该注解的变量信息
    public void addAnnotation(int id, VariableElement element) {
        if (!annotationMapInfo.containsKey(id)) {
            annotationMapInfo.put(id, element);
        }
    }

    //生成代码
    public JavaFile generateJavaCode() {
        MethodSpec.Builder methodSpec = MethodSpec.methodBuilder("findId")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(Void.class)
                .addParameter(TypeName.get(typeElement.asType()), "host", Modifier.FINAL)
                .addParameter(TypeName.OBJECT, "source");

        for (Map.Entry<Integer, VariableElement> entry : annotationMapInfo.entrySet()) {
            printNodeMessage("entry.getValue().getSimpleName().toString():"+entry.getValue().getSimpleName().toString());
            printNodeMessage("entry.getValue().asType().toString():"+entry.getValue().asType().toString());
            printNodeMessage("typeElement.getQualifiedName().toString():"+typeElement.getQualifiedName().toString());
            printNodeMessage("entry.getKey():"+entry.getKey());
            methodSpec.addStatement("host.$N = ($T)(($T)source).findViewById($L);",
                    entry.getValue().getSimpleName().toString(),
                    entry.getValue().asType().toString(),
                    typeElement.getQualifiedName().toString(),
                    entry.getKey());
        }

        TypeSpec typeSpec = TypeSpec.classBuilder(typeElement.getSimpleName().toString() + "$$ViewInject")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get("com.yueshen.xannotationprocessor", "XAPInterface"), TypeName.get(typeElement.asType())))
                .addMethod(methodSpec.build())
                .build();

        return JavaFile.builder(packageElement.getQualifiedName().toString(), typeSpec).build();
    }

    //输出信息
    private void printErrorMessage(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format("[ @%s --ERROR] " + message, FindId.class.getSimpleName()));
    }

    private void printNodeMessage(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format("[ @%s --NOTE] " + message, FindId.class.getSimpleName()));
    }
}
