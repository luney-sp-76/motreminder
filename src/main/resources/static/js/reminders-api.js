import { auth } from '/js/firebase-init.js?v=2';

async function callAPI(url, method, body = null) {
    if (!auth.currentUser) throw new Error('Not authenticated');
    const token = await auth.currentUser.getIdToken();
    const opts = {
        method,
        headers: { 'Authorization': 'Bearer ' + token, 'Content-Type': 'application/json' }
    };
    if (body !== null) opts.body = JSON.stringify(body);
    const res = await fetch(url, opts);
    if (!res.ok) throw new Error('API ' + method + ' ' + url + ' failed: ' + res.status);
    return method === 'GET' ? res.json() : null;
}

export const getReminders    = ()       => callAPI('/api/reminders', 'GET');
export const setReminders    = (data)   => callAPI('/api/reminders', 'POST', data);
export const patchReminders  = (data)   => callAPI('/api/reminders', 'PATCH', data);
export const deleteReminders = ()       => callAPI('/api/reminders', 'DELETE');
export const deleteVehicle   = (reg)    => callAPI('/api/reminders/vehicle/' + encodeURIComponent(reg), 'DELETE');
export const deleteUserDoc   = ()       => callAPI('/api/users', 'DELETE');
