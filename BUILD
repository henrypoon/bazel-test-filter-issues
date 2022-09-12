load("@rules_java//java:defs.bzl", "java_binary")
load("@rules_java//java:defs.bzl", "java_test")

java_binary(
    name = "greent",
    srcs = glob(["com/example/*.java"]),
)

java_test(
    name = "test",
    srcs = glob(["com/example/*.java"]),
    test_class = "com.example.TestGreeting",
    deps = [
        "@maven//:org_mockito_mockito_core",
    ]
)