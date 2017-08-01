/*
    This script collects the latest results of all jobs in your Jenkins.
    Run this script in a dedicated "notify" job, that gets triggered by other jobs.

    Set URL_JOB_STATUS to a correct value.
 */

import groovy.json.JsonOutput
import jenkins.model.*
import hudson.model.*


// Set your Ampelmaennchen endpoint here: http://{raspi-ip}/job-states
def URL_JOB_STATUS = "http://httpbin.org/post"

// collects all jobs and their last result
// mind: any currently running job (inc. the one that is executing this script) has no result yet
String[] results = Jenkins.instance.allItems.findResults {
    it.getLastBuild()?.getResult()?.toString()
}

def message = JsonOutput.toJson(results)
println("message: $message")

def connection = new URL(URL_JOB_STATUS).openConnection();
connection.with {
    doInput = true
    doOutput = true
    requestMethod = "POST"
    setRequestProperty("Accept", "*/*")
    setRequestProperty("Content-Type", "application/json")

    outputStream.withWriter { Writer writer ->
        writer << message
    }

    def response = inputStream.withReader { Reader reader -> reader.text }

    println("response code: ${responseCode}")
    println(response)
}