@echo off
setlocal enabledelayedexpansion

:: Tenter de trouver JavaHome pour les installations 64 bits et 32 bits
for /f "tokens=2* delims=    " %%a in ('reg query "HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Java Runtime Environment" /s /v JavaHome 2^>nul') do (
    set JAVA_HOME=%%b
)
if not defined JAVA_HOME (
    for /f "tokens=2* delims=    " %%a in ('reg query "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\JavaSoft\Java Runtime Environment" /s /v JavaHome 2^>nul') do (
        set JAVA_HOME=%%b
    )
)

:: Demander le chemin manuellement si nécessaire
if not defined JAVA_HOME (
    echo Java n'est pas trouvé sur ce système ou ne peut pas être localisé automatiquement.
    echo Veuillez entrer le chemin complet de votre installation Java. 
    echo Exemple : C:\Program Files\Java\jdk1.8.0_181
    
    set /p JAVA_HOME="Entrez le chemin ici : "
)

:: Vérifier la saisie
if not defined JAVA_HOME (
    echo Chemin Java non fourni, script terminé.
    pause
    exit
)

:: Créer un dossier temporaire pour le téléchargement et l'extraction
set TEMP_DIR=%~dp0temp
if exist "%TEMP_DIR%" rd /s /q "%TEMP_DIR%"
mkdir "%TEMP_DIR%"

:: Télécharger le fichier ZIP
echo Téléchargement de l'API Java Sound...
powershell -command "Invoke-WebRequest -Uri 'https://www.oracle.com/technetwork/java/soundbank-mid-149984.zip' -OutFile '%TEMP_DIR%\soundbank.zip'"
echo Téléchargement terminé.

:: Extraire le fichier
echo Extraction du fichier...
powershell -command "Expand-Archive -Path '%TEMP_DIR%\soundbank.zip' -DestinationPath '%TEMP_DIR%' -Force"
echo Extraction terminée.

:: Vérification de l'existence du fichier soundbank.gm
if not exist "%TEMP_DIR%\soundbank-mid.gm" (
    echo Le fichier soundbank.gm n'a pas été trouvé après l'extraction.
    pause
    exit
)

:: Vérifier si le dossier audio existe, sinon le créer
if not exist "%JAVA_HOME%\lib\audio" (
    mkdir "%JAVA_HOME%\lib\audio"
)

:: Déplacement du fichier soundbank.gm dans le dossier audio
echo Déplacement du fichier soundbank.gm...
move /Y "%TEMP_DIR%\soundbank-mid.gm" "%JAVA_HOME%\lib\audio\soundbank.gm"

:: Nettoyer le dossier temporaire
rd /s /q "%TEMP_DIR%"

:: Attendre que l'utilisateur appuie sur Entrée pour fermer le CMD
echo Appuyez sur Entrée pour fermer cette fenêtre...
pause >nul
