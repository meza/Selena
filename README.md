Selena - Yet another Selenium Java Framework

Selena is a fork of Skarslo's ( http://twitter.com/Skarlso) original WebAuthFW ( http://sourceforge.net/projects/webauthfw/ ).
The project aims to collect a set of tools and a set of processes that helps automating end-to-end tests via borwsers.

Requirements:
 * JDK (>=1.6)
 * ANT (>=1.7.1)

Uasge
 * Create a new directory for the test code.
 * Change to that directory.
 * Create a build.xml with the following content
<pre><code>
<project name="projectName" default="test-with-selenium">

    <!-- Set the default application specific properties -->
    <property name="selena.dir" value="${basedir}/../Selena" />

    <import file="${selena.dir}/build.xml"/>

    <target name="initProject" depends="Selena.initProject"/>

    <target name="test-with-selenium" depends="Selena.test-with-selenium"/>

    <target name="clean" depends="Selena.clean"/>

    <target name="build" depends="Selena.buildTestsuite"/>

    <target name="checkstyle" depends="Selena.checkstyle"/>

</project>
</code></pre>
 * issue <code>ant initProject</code>