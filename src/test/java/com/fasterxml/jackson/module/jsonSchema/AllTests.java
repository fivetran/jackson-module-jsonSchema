package com.fasterxml.jackson.module.jsonSchema;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EnumGenerationTest.class,
        EnumSchemaTest.class,
        HyperSchemaFactoryWrapperTest.class,
        TestCyclic.class,
        TestEmpty.class,
        TestEquals.class,
        TestGenerateJsonSchema.class,
        TestJDKTypes.class,
        TestJsonValue.class,
        TestPropertyOrderInSchema.class,
        TestReadJsonSchema.class,
        TestTypeGeneration.class,
        TestUnwrapping.class,
        TestVisitorContext.class,
        TitleSchemaFactoryWrapperTest.class,
        ValidationSchemaFactoryWrapperTest.class,
})
public class AllTests {
}
