package sparkDS.dataTypeComparison

import org.scalatest._
import sparkDS.testUtil.TestPrinting

class SchemaComparisonTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    TestPrinting.resetTestSuiteOutputDir(this)
  }

  "========================================================================================================================" +
    "\nCase 1 - Comparing StructType and StructType fields with same fields with same name and type" should "return true" in {
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = SchemaData.schema1,
      schema2Name = "schema1Same", schema2 = SchemaData.schema1Same)
    assert(comparisonResult.isSame, comparisonResult)
  }

  "\nCase 2 - Comparing StructType and StructType fields with same fields with same name and different type" should "return false" in {
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = SchemaData.schema1,
      schema2Name = "schema1FldDiffType", schema2 = SchemaData.schema1FldDiffType)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case2", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 3 - Comparing StructType and StructType fields with same fields with different name and same type" should "return false" in {
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = SchemaData.schema1,
      schema2Name = "schema2FldDiffTypeDiffName", schema2 = SchemaData.schema2FldDiffTypeDiffName)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case3", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

}