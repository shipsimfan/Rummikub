$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('me\shipsimfan\rummikub\cucumber\afterInitial30.feature');
formatter.feature({
  "line": 1,
  "name": "Playing different types of melds after the initial 30 points have been already played",
  "description": "",
  "id": "playing-different-types-of-melds-after-the-initial-30-points-have-been-already-played",
  "keyword": "Feature"
});
formatter.background({
  "line": 3,
  "name": "Play the initial 30 points",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "the initial 30 points have been played",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefPlay.playInitial30()"
});
formatter.result({
  "duration": 213929500,
  "error_message": "java.lang.ExceptionInInitializerError\r\n\tat cucumber.runtime.xstream.XStream.setupConverters(XStream.java:678)\r\n\tat cucumber.runtime.xstream.XStream.\u003cinit\u003e(XStream.java:456)\r\n\tat cucumber.runtime.converters.LocalizedXStreams$LocalizedXStream.\u003cinit\u003e(LocalizedXStreams.java:41)\r\n\tat cucumber.runtime.converters.LocalizedXStreams.newXStream(LocalizedXStreams.java:33)\r\n\tat cucumber.runtime.converters.LocalizedXStreams.get(LocalizedXStreams.java:25)\r\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:43)\r\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:223)\r\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\r\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\r\n\tat cucumber.runtime.model.CucumberScenario.runBackground(CucumberScenario.java:45)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:34)\r\n\tat cucumber.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:76)\r\n\tat cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:65)\r\n\tat cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:20)\r\n\tat org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)\r\n\tat org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)\r\n\tat org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:413)\r\n\tat cucumber.junit.FeatureRunner.run(FeatureRunner.java:72)\r\n\tat cucumber.junit.Cucumber.runChild(Cucumber.java:75)\r\n\tat cucumber.junit.Cucumber.runChild(Cucumber.java:36)\r\n\tat org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)\r\n\tat org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)\r\n\tat org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:413)\r\n\tat cucumber.junit.Cucumber.run(Cucumber.java:80)\r\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:93)\r\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:529)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:756)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:452)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)\r\n\tat ✽.Given the initial 30 points have been played(me\\shipsimfan\\rummikub\\cucumber\\afterInitial30.feature:4)\r\nCaused by: java.lang.reflect.InaccessibleObjectException: Unable to make field private final java.util.Comparator java.util.TreeMap.comparator accessible: module java.base does not \"opens java.util\" to unnamed module @21bcffb5\r\n\tat java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:357)\r\n\tat java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:297)\r\n\tat java.base/java.lang.reflect.Field.checkCanSetAccessible(Field.java:177)\r\n\tat java.base/java.lang.reflect.Field.setAccessible(Field.java:171)\r\n\tat cucumber.runtime.xstream.converters.collections.TreeMapConverter.\u003cclinit\u003e(TreeMapConverter.java:59)\r\n\tat cucumber.runtime.xstream.XStream.setupConverters(XStream.java:678)\r\n\tat cucumber.runtime.xstream.XStream.\u003cinit\u003e(XStream.java:456)\r\n\tat cucumber.runtime.converters.LocalizedXStreams$LocalizedXStream.\u003cinit\u003e(LocalizedXStreams.java:41)\r\n\tat cucumber.runtime.converters.LocalizedXStreams.newXStream(LocalizedXStreams.java:33)\r\n\tat cucumber.runtime.converters.LocalizedXStreams.get(LocalizedXStreams.java:25)\r\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:43)\r\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:223)\r\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\r\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\r\n\tat cucumber.runtime.model.CucumberScenario.runBackground(CucumberScenario.java:45)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:34)\r\n\tat cucumber.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:76)\r\n\tat cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:65)\r\n\tat cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:20)\r\n\tat org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)\r\n\tat org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)\r\n\tat org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:413)\r\n\tat cucumber.junit.FeatureRunner.run(FeatureRunner.java:72)\r\n\tat cucumber.junit.Cucumber.runChild(Cucumber.java:75)\r\n\tat cucumber.junit.Cucumber.runChild(Cucumber.java:36)\r\n\tat org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)\r\n\tat org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)\r\n\tat org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:413)\r\n\tat cucumber.junit.Cucumber.run(Cucumber.java:80)\r\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:93)\r\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:529)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:756)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:452)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)\r\n",
  "status": "failed"
});
formatter.scenario({
  "line": 6,
  "name": "Playing a valid 3-length run",
  "description": "",
  "id": "playing-different-types-of-melds-after-the-initial-30-points-have-been-already-played;playing-a-valid-3-length-run",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "I play \"O1\" to a new meld",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "I play \"O2\" to meld 1",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I play \"O3\" to meld 1",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I end my turn",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "the board should be \"{R10,R11,R12},{O1,O2,O3}\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "O1",
      "offset": 8
    }
  ],
  "location": "StepDefPlay.playNewMeld()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "O2",
      "offset": 8
    },
    {
      "val": "1",
      "offset": 20
    }
  ],
  "location": "StepDefPlay.playToMeld()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "O3",
      "offset": 8
    },
    {
      "val": "1",
      "offset": 20
    }
  ],
  "location": "StepDefPlay.playToMeld()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "StepDefPlay.endTurn()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "{R10,R11,R12},{O1,O2,O3}",
      "offset": 21
    }
  ],
  "location": "StepDefPlay.verifyBoard()"
});
formatter.result({
  "status": "skipped"
});
});