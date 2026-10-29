# [LAB-10]: Trabajo en equipo con GitHub (forks y Pull Requests)

## Prerequisitos

1. **Git**. [Link de Descarga](https://git-scm.com/install/). Descargar el instalador según su sistema operativo y seguir las instrucciones por defecto (Siguiente, siguiente, etc.).
2. **Visual Studio Code** (o su editor de preferencia). [Link de Descarga](https://code.visualstudio.com/download). Se recomienda instalar la extensión **Extension Pack for Java**.
3. Cuenta de GitHub. [Link de Registro](https://github.com/signup). Puede usar su cuenta personal o la de la universidad.
4. **JDK 17 o superior** (no basta con un JRE). [Link de Descarga](https://adoptium.net/) (Temurin 17). Descargue el instalador que corresponda a su procesador (en una Mac con chip Apple, la versión **aarch64**; en Windows, active las opciones *Set JAVA_HOME* y *Add to PATH*). Compruébelo con `java -version` y `javac -version`.

## Descripción del laboratorio

En este laboratorio **casi no se programa**: se aprende a **trabajar en equipo con GitHub**. Cada integrante agrega su **tarjeta** (una línea de código) en el **mismo archivo** de una aplicación de escritorio (Java) que muestra una ventana con las tarjetas de todo el equipo. Practicarán:

- crear un **fork** y trabajar en él,
- enviar sus cambios con un **Pull Request** al repositorio del equipo,
- **revisar y aprobar** los Pull Requests de sus compañeros,
- **traer** los cambios de sus compañeros (`fetch` y `merge`),
- **conocer los conflictos**: cuando dos personas cambian el mismo lugar del archivo (ocurrirá, y es normal). Cómo resolverlos se explica en clase.

| Archivo | ¿Se modifica? |
|---|---|
| `src/main/java/co/umariana/equipo/Integrantes.java` | **Sí.** Aquí agrega **su** tarjeta. Es el **mismo archivo para todo el equipo**. |
| `src/test/java/...` | **No.** Son las pruebas. |
| Todo lo demás | No es necesario modificarlo. |

### Dos repositorios, dos nombres

| Nombre | Qué es |
|---|---|
| **upstream** | El repositorio **del equipo** (donde se juntan los cambios de todos). |
| **origin** | **Su fork**: su copia personal del repositorio del equipo. Es el único donde usted puede subir cambios directamente. |

El flujo siempre es: trabajar en su fork → abrir un Pull Request hacia el repositorio del equipo → sus compañeros lo aprueban → se une a `main`.

## Ejecutar el proyecto

Desde una terminal **dentro de la carpeta del proyecto** (en Windows use **Git Bash**):

| Acción | Comando |
|---|---|
| Abrir la ventana con las tarjetas | `./scripts/start.sh` |
| Ejecutar las pruebas | `./scripts/test.sh` |

También puede abrir `src/main/java/co/umariana/equipo/Main.java` en VS Code y pulsar **Run** sobre `main`. La primera vez tarda unos minutos porque descarga lo necesario. Después de traer cambios de sus compañeros (Parte 2), cierre la ventana y ábrala de nuevo para ver las tarjetas nuevas.

## Parte 1: su tarjeta

### 1. Crear su fork

En GitHub, abra el repositorio **del equipo** y pulse **Fork** (arriba a la derecha) y luego **Create fork**. Quedará una copia suya en `https://github.com/SU-USUARIO/NOMBRE-DEL-REPOSITORIO`.

### 2. Clonar su fork

Copie la URL de **su fork** (botón verde **Code**) y ejecute:

```bash
git clone <URL-DE-SU-FORK>
cd <NOMBRE-DEL-REPOSITORIO>
```

### 3. Conectar el repositorio del equipo (`upstream`)

```bash
git remote add upstream <URL-DEL-REPOSITORIO-DEL-EQUIPO>
git remote -v
```

Debe ver `origin` (su fork) y `upstream` (el repositorio del equipo).

### 4. Crear una rama nueva

**No trabaje sobre `main`.**

```bash
git checkout -b LAB-10-su-usuario
```

### 5. Agregar su tarjeta

1. Abra la aplicación (`./scripts/start.sh`) y vea las tarjetas que ya existen.
2. Abra `src/main/java/co/umariana/equipo/Integrantes.java`. Justo **debajo** del comentario `// >>> AGREGUE SU TARJETA DEBAJO DE ESTA LÍNEA <<<` agregue **una** línea con su tarjeta (cambie los valores por los suyos):

   ```java
   lista.add(new Tarjeta("su-usuario-de-github", "Juan Pérez", "Una frase corta (máximo 80 caracteres)", "#06D6A0"));
   ```

   - **usuario:** su usuario de GitHub (letras, números y guiones).
   - **mensaje:** máximo 80 caracteres.
   - **color:** almohadilla y 6 dígitos (por ejemplo `#EF476F`, `#118AB2`, `#FFD166`).
3. Ejecute la aplicación de nuevo: debe ver su tarjeta.
4. Ejecute las pruebas: `./scripts/test.sh`. Si su tarjeta está mal escrita, le dirá qué corregir.

### 6. Guardar los cambios (commit)

```bash
git status
git add src/main/java/co/umariana/equipo/Integrantes.java
git commit -m "Agregar la tarjeta de Juan Pérez"
```

El mensaje debe estar en infinitivo y responder a la pregunta: "¿Qué hizo?"

### 7. Subir la rama a su fork (push)

```bash
git push origin LAB-10-su-usuario
```

### 8. Abrir el Pull Request hacia el repositorio del equipo

1. En GitHub, abra **su fork**: aparece un aviso amarillo con el botón **Compare & pull request**. Púlselo.
2. Compruebe que el destino (**base repository**) sea **el repositorio del equipo** y la rama `main`, y que el origen (**head repository**) sea su fork con su rama.
3. **Título:** empieza con `LAB-N`. Ejemplo: `LAB-10: tarjeta de Juan Pérez`.
4. **Descripción:** incluya una línea `Nombre: Su Nombre Completo`.
5. Pulse **Create pull request**.

### 9. Revisar los Pull Requests de sus compañeros

Un Pull Request **no se puede unir hasta que lo aprueben sus compañeros**: **2 aprobaciones** (o **1** si el equipo es de dos personas). No puede aprobar su propio Pull Request.

Para revisar el de un compañero: abra su Pull Request → pestaña **Files changed** → lea los cambios → **Review changes** → **Approve** → escriba un comentario → **Submit review**. Si algo está mal, elija **Request changes** y explique qué corregir.

Cuando tenga las aprobaciones necesarias, el botón **Merge pull request** se habilita y puede unirlo.

### 10. Si su Pull Request tiene conflictos

Todos agregaron su línea **en el mismo lugar** del mismo archivo. El primer Pull Request que se una entra sin problema, pero los demás mostrarán el aviso **"This branch has conflicts that must be resolved"**. Es normal: Git no sabe en qué orden poner las líneas y le pide que decida.

**Cómo resolverlo se explica en clase**, usando Visual Studio Code. Si le aparece este aviso, espere esa explicación y siga el paso a paso que se muestra allí.

## Parte 2: traer el trabajo de sus compañeros

Cuando se unan varios Pull Requests, el repositorio del equipo (`upstream`) tiene tarjetas que usted todavía no tiene en su `Integrantes.java`. Para traerlas:

```bash
git checkout main
git fetch upstream
git merge upstream/main
git push origin main
```

- `git fetch upstream` **descarga** la información del repositorio del equipo, sin cambiar sus archivos.
- `git merge upstream/main` **une** esos cambios con su `main`.
- `git push origin main` actualiza su fork.

(Alternativa en GitHub: en su fork, el botón **Sync fork**, y luego `git pull origin main`.)

Abra la aplicación otra vez: ahora debe ver las tarjetas de todo el equipo.
