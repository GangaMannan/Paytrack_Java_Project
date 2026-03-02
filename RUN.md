## Payroll System - Run Instructions

### ✅ Program Status
- **All compile errors: FIXED**
- **Both entry points working perfectly**

---

## How to Run

### Option 1: CLI (Command Line Interface)
```powershell
cd c:\Users\gmann\eclipse-workspace\Paytrack_Java_Project
java -p bin -m Paytrack_Java_Project/PayRoll.Main
```
Then interact with the menu by typing choices (1-8, or 0 to exit).

### Option 2: GUI (Graphical Interface)
```powershell
cd c:\Users\gmann\eclipse-workspace\Paytrack_Java_Project
java -p bin -m Paytrack_Java_Project/PayRoll.PayrollUI
```
Opens a Swing window with tabs for employees and transactions.

---

## Using VS Code (Recommended)

1. **Reload VS Code completely:**
   - Press `Ctrl+Shift+P`
   - Type "Java: Clean Java Language Server Workspace"
   - Select and run
   - Reload window: `Ctrl+R` or close/reopen
   
2. **Run from VS Code:**
   - Press `F5` or go to Run → Run (With Debug)
   - Select either "Main" (CLI) or "PayrollUI" (GUI)
   - Application launches in integrated terminal

3. **Red error squiggles will disappear** after the language server refreshes (usually 5-10 seconds after reload).

---

## What Was Fixed

✓ Package names unified with directory names (payRoll → PayRoll)  
✓ Module exports added to module-info.java  
✓ .classpath and .vscode/settings.json configured for Java 25  
✓ Launch configurations corrected  
✓ Language server cache cleared  

---

## Notes

- Data persists in `payroll.csv` in the project root
- Salary processing only works on the 5th of each month
- All 9 classes compile without errors
