
Ten plik zawiera uwagi ogólne. Dodatkowe informacje znajdują się w poszczególnych plikach i są zaznaczone tagiem "REV:".

* Niestety projekt się nie kompiluje. Zapomniałeś dodać source/target do pom.xml:
```
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
```

* Katalog 'bin' nie powinien być zacommitowany do gita.
