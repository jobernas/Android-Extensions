setProperty(){
  awk -v pat="^$1=" -v value="$1=$2" '{ if ($0 ~ pat) print value; else print $0; }' $3 > $3.tmp
  mv $3.tmp $3
}

getProperty(){
  grep "^$2=" "$1" | cut -d'=' -f2
}

echo "Increase build and version Number"

file="./gradle.properties"
if [ -f "$file" ]; then
  echo "$file found."

  moduleVersion=$( getProperty "${file}" "androidExtensionsName")
  moduleBuild=$( getProperty "${file}" "androidExtensionsBuild")

  echo "Module Version = "${moduleVersion}
  echo "Module Build = "${moduleBuild}

  echo "Incrementing Build Number"
  moduleBuild=$(($moduleBuild+1))
  echo "New Build Number = "${moduleBuild}

  setProperty "androidExtensionsBuild" "${moduleBuild}" "$file"
else
  echo "$file not found."
fi

echo ""
echo "Build AAR file for Module"
./gradlew androidextensions:assembleRelease

echo ""
echo "Publish in Maven"
./gradlew publish

echo ""
echo "Add Tag and Commit All"
git add -A && git commit -m "New Build for version ${moduleVersion} build number ${moduleBuild}"
git tag moduleVersion
git push origin --tags
