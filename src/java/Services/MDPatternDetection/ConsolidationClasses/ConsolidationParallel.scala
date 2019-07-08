package Services.MDPatternDetection.ConsolidationClasses

import java.io._
import java.util

import Services.MDfromLogQueries.Declarations.Declarations
import Services.MDfromLogQueries.Util.TdbOperation
import org.apache.jena.query.Dataset
import org.apache.jena.rdf.model._
import org.apache.jena.tdb.{TDB, TDBFactory}

import scala.collection.{JavaConverters, mutable}

object ConsolidationParallel {

  var modelsNumber = 0
  var originalModelsNumber = 0

  var dataset: Dataset = TDBFactory.createDataset()
  val tdbOperation = new TdbOperation()


  /** *************************************************** Functions ***********************************************************************/

  def consolidate2(): mutable.HashMap[String, Model] = {

    println(" consolidation ")
    //toStringModelsHashmap2(unpersistModelsMap(TdbOperation.dataSetAlleviatedUselessProperties))

    // val modelHashMap = TdbOperation.unpersistModelsMap(TdbOperation._toString)
    val modelHashMap = TdbOperation.unpersistModelsMap(Declarations.paths.get("_toString"))

    if (modelHashMap == null) return null
    var nb = 0
    var modelsHashMap: mutable.HashMap[String, Model] = convertToScalaMap(modelHashMap)


    var sizeOfResults: Int = modelsHashMap.size
    var sizeOfNewResults: Int = 0 // to compare it with the old one and exit the loop

    var nodeIterator: NodeIterator = null

    // loop until there is no consolidation possible i.e. the size of the map doesn't change
    while (sizeOfResults != sizeOfNewResults) {
      nb += 1
      println(s"la consolidation numero : $nb")
      val kies = modelsHashMap.keys
      sizeOfResults = sizeOfNewResults
      var nb_model = 0
      kies.foreach {
        key => {
          nb_model += 1
          println(s" model n°  $nb_model " + key)
          nodeIterator = modelsHashMap(key).listObjects

          // for all nodes in modelsHashMap
          while (nodeIterator.hasNext) {

            val node: RDFNode = nodeIterator.next
            // if node already exists as key (subject) in the map, and its model is not empty
            println(" je suis dans le while " + node.toString)

            if (modelsHashMap.contains(node.toString) && !modelsHashMap(node.toString).isEmpty) {

              // then consolidate it with the model in question
              modelsHashMap(key).add(modelsHashMap(node.toString))
              modelsHashMap.put(node.toString, ModelFactory.createDefaultModel)
            }
          }
        }
      }

      // clean the map from the empty models
      modelsHashMap = cleanMap(modelsHashMap)
      sizeOfNewResults = modelsHashMap.size
    }

    modelsNumber += modelsHashMap.size
    // println(" taille de la hashmap apres consolidation : " + modelsHashMap.size)
    modelsHashMap
  }

  def consolidate(): mutable.HashMap[String, Model] = {

    println(" consolidation ")
    //toStringModelHashMap(unpersistModelsMap(Declarations.paths.get("dataSetAlleviatedUselessProperties")), Declarations.paths.get("_toString") )

    //val modelHashMap = TdbOperation.unpersistModelsMap(TdbOperation._toString)
    val modelHashMap = TdbOperation.unpersistModelsMap(Declarations.paths.get("_toString"))

    if (modelHashMap == null) return null
    var nb = 0
    var modelsHashMap: mutable.HashMap[String, Model] = convertToScalaMap(modelHashMap)

    var nodeIterator: NodeIterator = null
    var consolidatedNodes: mutable.HashSet[String] = new mutable.HashSet[String]()
    modelsHashMap.foreach {
      pair => {
        val model = pair._2
        nb+=1
        println ("consolidation model num : "+nb)
        nodeIterator = model.listObjects
        var newSizeOfObjects = 0
        var sizeofObjects = nodeIterator.toList.size()
        // for all nodes in modelsHashMap
        while (sizeofObjects != newSizeOfObjects) {
          sizeofObjects = newSizeOfObjects
          nodeIterator = model.listObjects()
         // println("je rentre ici")
          while (nodeIterator.hasNext) {
            val node: RDFNode = nodeIterator.next
            // if node already exists as key (subject) in the map, and its model is not empty
          //  println(" je suis dans le while " + node.toString)
            if (modelsHashMap.contains(node.toString) && !model.containsAll(modelHashMap.get(node.toString)) && !modelsHashMap(node.toString).isEmpty ) {
              // then consolidate it with the model in question
              model.add(modelsHashMap(node.toString))
              consolidatedNodes.add(node.toString)
            }
          }
          newSizeOfObjects = model.listObjects().toList.size()
        }

      }
    }
    consolidatedNodes.foreach {
      nodeName => {
      //  println(nodeName)

         // println("je rentre")
          modelsHashMap -= nodeName
          // modelsHashMap.remove(nodeName)

      }
    }
    modelsNumber += modelsHashMap.size
    // println(" taille de la hashmap apres consolidation : " + modelsHashMap.size)
    modelsHashMap
  }

  def convertToScalaMap(modelHashMap: util.HashMap[String, Model]): mutable.HashMap[String, Model] = {

    val kies = modelHashMap.keySet()
    val result: mutable.HashMap[String, Model] = new mutable.HashMap[String, Model]()

    kies.forEach(
      key => {
        try {
          if (key != null && modelHashMap.get(key) != null) {
            result.put(key, modelHashMap.get(key))
          }
        }
        catch {
          case e: Exception =>
        }
      }

    )
    result
  }

  def cleanMap(map: mutable.HashMap[String, Model]): mutable.HashMap[String, Model] = {

    val newResults = new mutable.HashMap[String, Model]


    map.foreach {
      pair => {
        if (!pair._2.isEmpty) {
          newResults.put(pair._1, pair._2)
        }
        else println("vide " + pair._1)
      }

    }
    newResults
  }

  def toStringModelsHashmap2(it: Iterator[String], tdbPath: String) = {
    val iterator = it
    val modelHashMap = new mutable.HashMap[String, Model]
    var modelsFromOneModel = new mutable.HashMap[String, Model]
    var nb = 0
    iterator.grouped(10000).foreach {
      listOfKies =>
        listOfKies.foreach {
          key => {

            val model = getModelFromTDB(key, dataset)
            nb += 1
            originalModelsNumber += 1

            System.out.println("consolidation model num " + nb)
            modelsFromOneModel = getModelsofModel(model)
            import scala.collection.JavaConversions._
            for (key2 <- modelsFromOneModel.keySet) {

              if (modelHashMap.containsKey(key2)) {
                modelHashMap.replace(key2, modelHashMap(key2).union(modelsFromOneModel(key2)))
              }
              else modelHashMap.put(key2, modelsFromOneModel(key2))
            }

          }

        }

        for (key2 <- modelsFromOneModel.keySet) {

          if (modelHashMap.get(key2).size>200) {
            modelHashMap.remove(key2)
          }

        }

        println(s" ------------------------- finish with the group ------------------------------- ")
        writeInTdb(modelHashMap, tdbPath)
        modelHashMap.clear()
    }
    TdbOperation._toString.close()

  }

  def toStringModelHashMap(it: Iterator[String], tdbPath: String): Unit = {
    val iterator = it
    var num = 0
    var nb_grp = 0
    val modelHashMap: mutable.HashMap[String, Model] = mutable.HashMap()

    iterator.grouped(50000).foreach {

      listOfModelNames =>
        listOfModelNames.foreach {
          nb_grp += 1
          modelName => {
            val model = getModelFromTDB(modelName, TdbOperation.dataSetAlleviatedUselessProperties)

            num += 1
            System.out.println("tostring model num : " + num)

            val list = model.listStatements

            // For every Statement in the model
            while (list.hasNext) {

              val statement = list.next
              val subject = statement.getSubject.toString
              // if the pair doesn't exist in the map create a new instance
              if (!modelHashMap.contains(subject)) {
                modelHashMap.put(subject, ModelFactory.createDefaultModel)
                modelHashMap(subject).add(statement)
              }
              else { // add the statement to the corresponding model
                modelHashMap(subject).add(statement)
              }
            }

          }
        }
        for (key2 <- modelHashMap.keySet) {

          if (modelHashMap.get(key2).size>200) {
            modelHashMap.remove(key2)
          }

        }
        writeInTdb(modelHashMap, tdbPath)
        modelHashMap.clear()
        println(s" ------------------------- finish with the group number: $nb_grp -------------------------------- ")
    }

  }

  /** Unpersisting **/

  def getModelFromTDB(modelName: String, dataset: Dataset): Model = {
    val model = dataset.getNamedModel(modelName)
    model
  }

  def writeInTdb(models: mutable.HashMap[String, Model], datasetName: String) = {

    println(" nombres des models pour persisting " + models.size)
    val dataset = TDBFactory.createDataset(datasetName)
    models.foreach(m => {
      try {
        if (m != null) {
          if (dataset.containsNamedModel(m._1)) {
            val model = dataset.getNamedModel(m._1).union(m._2)
            dataset.replaceNamedModel(m._1, model)
          }
          else
            dataset.addNamedModel(m._1, m._2)
        }
      }
      catch{
        case ex: Exception => ex.printStackTrace()
      }
    })
    TDB.sync(dataset)
    dataset.close()
  }

  def getModelsofModel(model: Model): mutable.HashMap[String, Model] = {
    val resourceList = model.listSubjects.toList
    val rdfNodeList = model.listObjects.toList
    val modelHashMap = new mutable.HashMap[String, Model]
    import scala.collection.JavaConversions._
    for (resource <- resourceList) {
      val resourceModel = ModelFactory.createDefaultModel
      if (!rdfNodeList.contains(resource)) {
        var visitedNodes = new mutable.HashSet[RDFNode]()
        visitedNodes.add(resource)
        modelHashMap.put(resource.getURI, getModelOfResource(resource, resourceModel, visitedNodes))
      }
    }
    modelHashMap
  }

  def getModelOfResource(resource: Resource, model: Model, visitedNodes: mutable.HashSet[RDFNode]): Model = {
    val stmtIterator = resource.listProperties
    var internModel = stmtIterator.toModel

    val list = resource.listProperties.toList
    import scala.collection.JavaConversions._
    for (statement <- list) {
      val contains = visitedNodes.contains(
        statement.getObject.asResource)
      if (!contains) {
        visitedNodes.add(statement.getObject)
        internModel.add(getModelOfResource(statement.getObject.asResource, internModel, visitedNodes))
      }
      else internModel = internModel.remove(statement)
    }
    internModel
  }


  def unpersistModelsMap(datasetName: String): Iterator[String] = {

    dataset = TDBFactory.createDataset(datasetName)
    val results = new mutable.HashMap[String, Model]

    TDB.sync(dataset)

    JavaConverters.asScalaIterator(dataset.listNames())
  }

  def WriteListSubjectsInFile(writingFilePath: String, list: List[String]): Unit = {
    val file = new File(writingFilePath)
    var bw = null
      if (!file.isFile) file.createNewFile
try{
  val writer = new PrintWriter(new File(writingFilePath))
  list.foreach(subject => if (subject != null) {

    writer.write(subject.replaceAll("[\n\r]", "\t") + "\n")
  })

  writer.close()
}




  }


  def consolidateParallel(tdbPath : String) = {

    println(" consolidation ")
   // toStringModelHashMap(unpersistModelsMap(Declarations.paths.get("dataSetAlleviatedUselessProperties")), Declarations.paths.get("_toString") )
    /** use an iterator not to load the models into memory **/
    var iterator = unpersistModelsMap(Declarations.paths.get("dataSetAlleviated_B4"))
    /** use a list to verify if the node exists as a subject **/
    val listOfModels = iterator.toList
    /** open tdb to lod models directly from it **/
    val dataset_toString = TDBFactory.createDataset(Declarations.paths.get("dataSetAlleviated_B4"))

    /** Hashmap to persist consolidated models **/
    var modelHashMap = new mutable.HashMap[String, Model]()

    var nb = 0
    var nblevels = 0
    var listOfObjects : util.List[RDFNode] = null
    var nodeIterator: util.Iterator[RDFNode] = null
    val consolidatedNodes: mutable.HashSet[String] = new mutable.HashSet[String]()
    iterator = listOfModels.iterator
    iterator.grouped(50000).foreach {
      listOfKies =>
        listOfKies.foreach {
          key => {
            nb += 1
            println(s"la consolidation numero : $nb")
            var model = getModelFromTDB(key, dataset_toString)
            listOfObjects = model.listObjects().toList
            var newSizeOfObjects = listOfObjects.size()
            var sizeofObjects = 0
            // for all nodes in modelsHashMap
            nblevels = 0
            if (newSizeOfObjects <=30)
              while (sizeofObjects != newSizeOfObjects && newSizeOfObjects <= 30 && nblevels<4) {
                nblevels+=1
              //  println("size of new objects : "+newSizeOfObjects + " size of objects : "+sizeofObjects)
                sizeofObjects = newSizeOfObjects
                nodeIterator = listOfObjects.iterator()
                while (nodeIterator.hasNext) {
                  val node: RDFNode = nodeIterator.next
                  // if node already exists as key (subject) in the map, and its model is not empty
                  if (listOfModels.contains(node.toString)) {
                    val newModel = getModelFromTDB(node.toString, dataset_toString)
                    if (!model.containsAll(newModel) && newModel.size() < 20)
                    // then consolidate it with the model in question
                    {
                      model.add(newModel)
                      consolidatedNodes.add(node.toString)
                    }
                  }
                }
                listOfObjects = model.listObjects().toList
                newSizeOfObjects = listOfObjects.size()
              }
          /*  else
            {
              val reducedModel = ModelFactory.createDefaultModel()
              val it = model.listStatements()
              var nbStm = 0
              while (it.hasNext && nbStm<20)
              {
                nbStm+=1
                reducedModel.add(it.next)
              }
              model = reducedModel
            }*/
            modelHashMap.put(key,model)
          }
        }

        consolidatedNodes.foreach{
          nodeName => {
            if (modelHashMap.contains(nodeName))
              modelHashMap.remove(nodeName)
          }
        }

        //modelHashMap = removeRedundantTriples(modelHashMap)
        println(s" ------------------------- finish with the group ------------------------------- ")
        modelsNumber += modelHashMap.size
        writeInTdb(modelHashMap, tdbPath)
        modelHashMap.clear()
    }

  }


}
