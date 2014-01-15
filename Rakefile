require 'fileutils'

HOME_DIR = "#{File.dirname(__FILE__)}"

API_DIR = "#{HOME_DIR}/api"
API_ARTIFACT_NAME = "api-fat.jar"
API_ARTIFACT = "#{API_DIR}/build/libs/#{API_ARTIFACT_NAME}"
API_CONFIGURATION_PATH = "src/dist/config/api.yml" #required for stop:api
API_CONFIGURATION = "#{API_DIR}/#{API_CONFIGURATION_PATH}"

WEB_DIR = "#{HOME_DIR}/web"
WEB_ARTIFACT_NAME = "web-fat.jar"
WEB_ARTIFACT = "#{WEB_DIR}/build/libs/#{API_ARTIFACT_NAME}"
WEB_CONFIGURATION_PATH = "src/dist/config/web.yml" #required for stop:web
WEB_CONFIGURATION = "#{WEB_DIR}/#{WEB_CONFIGURATION_PATH}"

FAT_JAR_PORT_API=8113
FAT_JAR_ADMIN_PORT_API=8114
FAT_JAR_PORT_WEB=8110
FAT_JAR_ADMIN_PORT_WEB=8111

STATUS_CHECK_PATH="/ping"

task :default => 'build:all'

namespace :build do
  desc "Build, test and create artifact"

  task :all do
    Rake::Task['build:api'].invoke
    Rake::Task['build:web'].invoke
  end

  task :api do
    puts "Building Api"
    fail "API BUILD FAILED" unless system 'gradle -p api clean test fatJar'
  end

  task :web do
    puts "Building Web"
    fail "WEB BUILD FAILED" unless system 'gradle -p web clean test check fatJar'
  end

  task :shared do
    puts "Building Shared"
    fail "SHARED BUILD FAILED" unless system 'gradle -p shared clean test check'
  end
end

namespace :run do
  task :all do #Have to execute rake tasks because "task :all => [:api, :web]" can not be called from another rake task
    Rake::Task['run:api'].execute
    Rake::Task['run:web'].execute
  end

  task :api do
    start_application(API_DIR)
  end

  task :api_fat do
    start_fat_jar(API_ARTIFACT, API_CONFIGURATION)
  end

  task :web do
    puts "!!!!!!!!!!!!!"
    puts WEB_CONFIGURATION_PATH
    start_application(WEB_DIR)
  end

  task :web_fat do
    start_fat_jar(WEB_ARTIFACT, WEB_CONFIGURATION)
  end
end

namespace :stop do
  task :all do #Have to execute rake tasks because "task :all => [:api, :web]" can not be called from another rake task
    Rake::Task['stop:web'].execute
    Rake::Task['stop:api'].execute
  end

  task :api do
    kill_process_by_name(API_CONFIGURATION_PATH)
  end

  task :web do
    kill_process_by_name(WEB_CONFIGURATION_PATH)
  end
end

def start_fat_jar(jar_file, jar_configuration)
  process = fork do
    puts "Starting fat jar: java -jar '#{jar_file}' server '#{jar_configuration}'"
    exec "java -jar '#{jar_file}' server '#{jar_configuration}'"
  end
  Process.detach(process)
end

def start_application(directory)
  process = fork do
    puts "Starting application in: '#{directory}'"
    exec "cd '#{directory}' && gradle run"
  end
  Process.detach(process)
end

def kill_process_by_name(name)
  puts "Trying to stop #{name}"
  `kill \`ps -ef | grep #{name} | grep -v grep | awk '{print $2}'\``
end
