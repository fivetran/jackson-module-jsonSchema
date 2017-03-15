package(default_visibility = ["//visibility:public"])

java_library(
    name = "jackson_module_json_schema",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "@jackson_core//jar",
        "@jackson_annotations//jar",
        "@javax_validation_api//jar",
        "@jackson_databind//jar",
    ],
)

java_test(
    name = "tests",
    srcs = glob(["src/test/java/**/*.java"]),
    test_class = "com.fasterxml.jackson.module.jsonSchema.AllTests",
    deps = [
        ":jackson_module_json_schema",
        "@jackson_core//jar",
        "@jackson_annotations//jar",
        "@javax_validation_api//jar",
        "@jackson_databind//jar",
        "@hamcrest_core//jar",
        "@junit//jar",
    ],
)