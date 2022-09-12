This repo can reproduce the following two issues around `unnecessary stubbings` on Bazel with Java JUnit test and Mockito

1. `unnecessary stubbings` errors are swallowed and test passed when `test_filter` is being used, however, it failed as expected without `test_filter`
2. `unnecessary stubbings` errors are NOT reported in JUnit reports regardless of using `test_filter` or not. The JUnit report shows all tests have passed.

### How to reproduce?
`com/example/TestGreeting.java` contains unnecessary stubbings in `setup` while the test is just checking 1 equals to 1. 

1. Run `bazel test //:test` - The test failed as expected when we only specify the whole target to run. However, there are no such errors are mentioned from JUnit reports.

Terminal log
```
INFO: Elapsed time: 1.389s, Critical Path: 1.16s
INFO: 6 processes: 4 internal, 1 darwin-sandbox, 1 worker.
INFO: Build completed, 1 test FAILED, 6 total actions
//:test                                                                  FAILED in 1.0s
```

Test log
```
JUnit4 Test Runner
.E
Time: 0.358
There was 1 failure:
1) unnecessary Mockito stubbings(com.example.TestGreeting)
org.mockito.exceptions.misusing.UnnecessaryStubbingException:
Unnecessary stubbings detected in test class: TestGreeting
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at com.example.TestGreeting.setUp(TestGreeting.java:19)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
	at org.mockito.internal.runners.StrictRunner.run(StrictRunner.java:53)
	at org.mockito.junit.MockitoJUnitRunner.run(MockitoJUnitRunner.java:163)
	at com.google.testing.junit.runner.internal.junit4.CancellableRequestFactory$CancellableRunner.run(CancellableRequestFactory.java:108)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
	at com.google.testing.junit.runner.junit4.JUnit4Runner.run(JUnit4Runner.java:116)
	at com.google.testing.junit.runner.BazelTestRunner.runTestsInSuite(BazelTestRunner.java:148)
	at com.google.testing.junit.runner.BazelTestRunner.main(BazelTestRunner.java:75)

FAILURES!!!
Tests run: 1,  Failures: 1
```

JUnit Report
```
<?xml version='1.0' encoding='UTF-8'?>
<testsuites>
  <testsuite name='com.example.TestGreeting' timestamp='2022-09-12T07:02:05.488Z' hostname='localhost' tests='1' failures='0' errors='0' time='0.095' package='' id='0'>
    <properties />
    <testcase name='testRunner' classname='com.example.TestGreeting' time='0.095' />
    <system-out />
    <system-err /></testsuite></testsuites>
```

2. Run `bazel test //:test --test_filter=TestGreet`. The test has passed and no errors are mentioned in the JUnit report.

Terminal log
```
INFO: Analyzed target //:test (0 packages loaded, 1 target configured).
INFO: Found 1 test target...
Target //:test up-to-date:
  bazel-bin/test.jar
  bazel-bin/test
INFO: Elapsed time: 1.312s, Critical Path: 1.08s
INFO: 2 processes: 1 internal, 1 darwin-sandbox.
INFO: Build completed successfully, 2 total actions
//:test                                                                  PASSED in 1.0s
```