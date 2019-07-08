package Services.MDPatternDetection.Alleviation

import Services.MDfromLogQueries.Util.{BasicProperties, GenericClasses}
import org.apache.jena.rdf.model.{Model, ModelFactory}

import scala.collection.mutable

object MdGraphsAlleviationParallel {

  var numberModelsAlleviated = 0
  var numberModelsRemoved = 0
  var numberStatementRemoved = 0


  def   MDGraphsAlleviate(hashMapModels: mutable.HashMap [String, Model]): mutable.HashMap [String, Model] = {

    val mdGraphsAlleviated=new mutable.HashMap[String, Model]()
    val mdGraphsLessThen2=new mutable.HashMap[String, Model]()
    var number=0

    try {

      hashMapModels.par.foreach(pair =>{
        number+=1
        println("Alleviation model number" + number)

        if (pair._2 != null && (pair._2.size > 2 || pair._2.size <50)) mdGraphsAlleviated.put(pair._1, pair._2)
        else

          {
            if (pair._2.size >50){
              val reducedModel = ModelFactory.createDefaultModel()
              val it = pair._2.listStatements()
              var nbStm = 0
              while (it.hasNext && nbStm<20)
              {
                nbStm+=1
                reducedModel.add(it.next)
              }
              mdGraphsAlleviated.put(pair._1, reducedModel)
            }
            else mdGraphsLessThen2.put(pair._1, pair._2)
          }
      })

    } catch {
      case ignoredException: NullPointerException =>

    }


    // TODO à enlever si y a pas besoin de sauvegarder
    //    TdbOperation.persistHashMap(MDGraphAlleviated, TdbOperation.dataSetAlleviated);
    // TdbOperation.persistHashMap(MDGraphLessThen2, Declarations.paths.get("dataSetNonAlleviated"));


    numberModelsAlleviated += mdGraphsAlleviated.size
    numberModelsRemoved += mdGraphsLessThen2.size
    mdGraphsAlleviated
  }


  def removeUselessProperties(modelHashMap: mutable.HashMap[String, Model]): mutable.HashMap[String, Model] = {
    new BasicProperties
    new GenericClasses
    val modifiedModels = new mutable.HashMap[String, Model]
    var model:Model = null
    var i = 0

    try {

      modelHashMap.par.foreach(pair=>{
        i += 1
        System.out.println(" Alleviation le model n° " + i)
        model = pair._2
        val stmtList = model.listStatements.toList
        stmtList.forEach(statement=>{


            if (BasicProperties.properties.contains(statement.getPredicate)
              || GenericClasses.resources.contains(statement.getObject.asResource)
              || GenericClasses.resources.contains(statement.getSubject)) {
              model.remove(statement)
              numberStatementRemoved += 1
            }

        })

        modifiedModels.put(pair._1, model)
        })



   } catch {
      case ignoredException: NullPointerException =>

    }
    modifiedModels
  }


}
