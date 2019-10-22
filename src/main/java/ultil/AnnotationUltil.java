package ultil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class AnnotationUltil {
    /**
     * The {@link AnnotationUltil#scanAnnotations(Class, Class)} scans all classes annotated @param clazz in the project
     * @param annotation identify what annotation the classes are annotated
     * @param classRoot scan from package name
     * @return A {@link Class} annotated the @param annotation array
     */
    public Class[] scanAnnotations(Class annotation,Class classRoot) throws IOException, ClassNotFoundException {
        //get all package
        Package[] packages = null ;
        packages = scanPackage(classRoot);
        //get all class in each package
        List<Class> anClassList = new Vector<Class>();
        Class[] classes = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for(Package packag: packages){
            classes = getClasses(packag.getName());
            for(Class cla: classes){
                //if the class is annotated
                if(cla.getAnnotation(annotation) != null){
                    anClassList.add(cla);
                }
            }
        }
        return anClassList.toArray(new Class[anClassList.size()]);
    }

    /**
     * The {@link AnnotationUltil#scanPackage(Class)} scans all package in running time
     * @return A {@link Package} list
     */
    private Package[] scanPackage(Class classRoot){
        //get all packages at running time
        Package[] packages = Package.getPackages();
        //all packages of classRoot
        List<Package> clRoPackages = new Vector<Package>();
        for (int i = 0; i < packages.length; i++) {
            Package p = packages[i];
            //if package name start with classRoot package name -> valid
            if(p.getName().startsWith(classRoot.getPackageName())){
                clRoPackages.add(p);
            }
        }
        Package[] rs = new Package[clRoPackages.size()];
        return  clRoPackages.toArray(rs);
    }
    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
