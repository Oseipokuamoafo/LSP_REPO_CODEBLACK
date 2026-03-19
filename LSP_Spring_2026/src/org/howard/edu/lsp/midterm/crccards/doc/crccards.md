# CRC Delegation Explanation
TaskManager collaborates with Task because it needs Task objects to do its job storing them, adding new ones, finding one by ID, and filtering by status.
 Task on the other hand doesn't need to know anything about TaskManager. Its only job is to hold its own data and update its own status, so there's no reason for it to depend on anything else. 
 The collaboration only goes one way because that's all that's needed ,a class should only rely on another class if it actually needs it to get its own work done.
