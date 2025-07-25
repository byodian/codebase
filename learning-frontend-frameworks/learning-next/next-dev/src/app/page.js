import Counter from "@/app/ui/counter"
import ServerComponent from "@/app/components/ServerComponent";
import ClientComponent from "@/app/components/ClientComponent";

export default function Home() {
  return (
      <div>
        <p>Hello World!</p>
        <p>Hello Next.js</p>
        <Counter />
        <ClientComponent>
          <ServerComponent />
        </ClientComponent>
      </div>
  )
}
