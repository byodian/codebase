'use client'
export default function ClientComponent({children}) {
    return <div className="client-component">
        This is client-rendered
        {children}
    </div>
}