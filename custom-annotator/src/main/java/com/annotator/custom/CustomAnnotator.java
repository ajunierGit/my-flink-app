package com.annotator.custom;


import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;

public class CustomAnnotator extends AbstractAnnotator {

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);

        // Note: does not have to be the propertyName, could be the field or propertyNode that is verified.
        if (propertyName.equals("friends")) {

            JClass typeInfo = clazz.owner().ref("org.apache.flink.api.common.typeinfo.TypeInfo");
            JClass factory = clazz.owner().ref("com.example.typeInfo.FriendListTypeInfoFactory");
            field.annotate(typeInfo).param("value", factory.dotclass());

        }
    }
}
