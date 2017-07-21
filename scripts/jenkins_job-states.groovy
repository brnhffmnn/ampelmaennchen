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

def post = new URL(URL_JOB_STATUS).openConnection()
String message = JsonOutput.toJson(results)

post.setRequestMethod("POST")
post.setDoOutput(true)
post.setRequestProperty("Content-Type", "application/json")
post.getOutputStream().write(message.getBytes("UTF-8"))

def postRC = post.getResponseCode()
if (postRC == 200) {
    println(post.getInputStream().getText())
}
